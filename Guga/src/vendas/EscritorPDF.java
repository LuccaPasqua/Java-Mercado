package vendas;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class EscritorPdf {
    public void escreverNotaFiscal(String nomeArquivo, Venda venda) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();

            document.add(new Paragraph("NOTA FISCAL\n"));
            document.add(new Paragraph("Venda ID: " + venda.getId()));
            document.add(new Paragraph("Cliente: " + venda.getCliente().getNome()));

            document.add(new Paragraph("\nItens da venda:"));
            for (ItemVenda item : venda.getItens()) {
                String linha = String.format("- %s | Qtd: %d | Unitário: R$%.2f | Total: R$%.2f",
                        item.getNome(), item.getQuantidade(), item.getPreco(), item.getTotal());
                document.add(new Paragraph(linha));
            }

            document.add(new Paragraph("\nDesconto aplicado: " + venda.getDesconto() + "%%"));
            document.add(new Paragraph("Total final: R$" + String.format("%.2f", venda.getValorTotal())));

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter hf = DateTimeFormatter.ofPattern("HH:mm");
            document.add(new Paragraph("\nData: " + venda.getDataHora().format(df)));
            document.add(new Paragraph("Hora: " + venda.getDataHora().format(hf)));

        } catch (DocumentException | IOException e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
