package application;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.InflaterInputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.scene.control.TreeItem;
import model.CD;
import model.CDList;


//https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/
public class CDTreeCreator {

	public static TreeItem<String> createTree(String filename) {
		TreeItem<String> rootItem = new TreeItem<>();
		rootItem.setValue("Catalog");
		//json
		try {
			Reader reader = new InputStreamReader(CDTreeCreator.class.getResourceAsStream(filename),"UTF-8");
			Gson gson = new GsonBuilder().create();
			CDList cdlist = gson.fromJson(reader, CDList.class);// aus json Javaobjekte Class<CDList>
			System.out.println(cdlist);// 
			for (CD cd : cdlist.getCdlist()) {
				TreeItem<String> cdItem = new TreeItem<>(cd.getTitle());
				TreeItem<String> titleItem = new TreeItem<>("Title: "+cd.getTitle());
				TreeItem<String> artisItem = new TreeItem<>("Artist: "+cd.getArtist());
				TreeItem<String> countryItem = new TreeItem<>("Country: "+cd.getCountry());
				TreeItem<String> companyItem = new TreeItem<>("Company: "+cd.getCompany());
				TreeItem<String> priceItem = new TreeItem<>("Price: "+cd.getPrice());
				TreeItem<String> yearItem = new TreeItem<>("Year: "+ cd.getYear());
				
				cdItem.getChildren().add(titleItem);
				cdItem.getChildren().add(artisItem);
				cdItem.getChildren().add(countryItem);
				cdItem.getChildren().add(companyItem);
				cdItem.getChildren().add(priceItem);
				cdItem.getChildren().add(yearItem);
				
				
				
				//rootItem.getChildren().addAll(cdItem,artisItem);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rootItem;
	}

}
