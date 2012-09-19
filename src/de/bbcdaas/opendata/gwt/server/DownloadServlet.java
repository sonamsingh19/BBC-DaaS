package de.bbcdaas.opendata.gwt.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import de.bbcdaas.opendata.gwt.shared.DownloadFormats;

public class DownloadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String datasetId = req.getParameter("DatasetId");
		String format = req.getParameter("format");
		String fileName = (System.getProperty("java.io.tmpdir") + datasetId + ".csv");
		OutputStream out = resp.getOutputStream();

		switch (DownloadFormats.valueOf(format)) {
		case CSV: {
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ datasetId + ".csv");
			FileInputStream fIn = new FileInputStream(fileName);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = fIn.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			fIn.close();
		}

			break;
		case XML: {
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ datasetId + ".xml");
			List<String[]> entries = readCSV(fileName);

			XStream xStream = new XStream();
			
			String xml = xStream.toXML(entries);

			out.write(xml.getBytes());

		}
			break;
		case JSON:

		{
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ datasetId + ".json");
			
			List<String[]> entries = readCSV(fileName);

		  XStream xstream = new XStream(new JettisonMappedXmlDriver());
		        xstream.setMode(XStream.NO_REFERENCES);
		    
		    String json = xstream.toXML(entries);		
		       
			out.write(json.getBytes());
		}
			break;

		default:
			break;
		}

		out.flush();

	}


	private List<String[]> readCSV(String fileName) throws IOException {
		FileInputStream fstream = new FileInputStream(fileName);

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		CSVReader reader = new CSVReader(br);
		return reader.readAll();
	}
}
