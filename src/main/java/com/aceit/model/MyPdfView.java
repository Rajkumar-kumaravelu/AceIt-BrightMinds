package com.aceit.model;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyPdfView extends AbstractPdfView {
	
	Answer ans;
    
    public MyPdfView(Answer ans){
        this.ans = ans;
    }

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Paragraph question = new Paragraph("Question : " + ans.getAnsweredQuestion());
        Paragraph answer = new Paragraph("Answer : " + ans.getAnswer());
        
        document.add(question);
        document.add(answer);
		
	}

}
