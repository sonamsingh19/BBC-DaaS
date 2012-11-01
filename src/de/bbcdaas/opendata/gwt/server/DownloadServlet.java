package de.bbcdaas.opendata.gwt.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSets;
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
		
			List<ArrayList<String>> csvLines=getCSVFormat(datasetId)	;	
			String separator=",";
			String fileString="";
			 for (int i = 0; i < csvLines.size(); i++) {
			    ArrayList<String> row=	csvLines.get(i);
			    String rowString="";
			    for (String value : row) {
				rowString=rowString+value+separator;
				}
			   rowString= rowString.substring(0, rowString.length()-separator.length());
			    fileString=fileString+rowString;

			    System.out.println(rowString);
		
			}
		
				out.write(fileString.getBytes());
			
			
		}

			break;
		case XML: {
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ datasetId + ".xml");
			List<ArrayList<String>> entries = getCSVFormat(datasetId);

			XStream xStream = new XStream();

			String xml = xStream.toXML(entries);

			out.write(xml.getBytes());

		}
			break;
		case JSON:

		{
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ datasetId + ".json");

			List<ArrayList<String>> entries = getCSVFormat(datasetId);

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

	private List<ArrayList<String>> getCSVFormat(String datasetId) {
		List<ArrayList<String>> csvLines = new ArrayList<ArrayList<String>>();
		DataSet dataSet = DataSets.dataSets.get(datasetId);
		ArrayList<DataSetColumn> columns = dataSet.getColumns();
		DataSetServiceImpl dataSetServiceImpl = new DataSetServiceImpl();
		ArrayList<String> datasetCols = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			datasetCols.add(columns.get(i).getName());
		}
		csvLines.add(datasetCols);
		csvLines.addAll(dataSetServiceImpl.convertToRowWise(dataSet));
		return csvLines;
	}
}
