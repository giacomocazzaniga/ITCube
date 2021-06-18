package itcube.consulting.monitoraggioClient.response;

import java.util.HashMap;
import java.util.List;

import itcube.consulting.monitoraggioClient.entities.database.TipologiaClientCount;

public class GetClientTypesResponse extends GeneralResponse{
	private List<TipologiaClientCount> categories;

	public GetClientTypesResponse() {
	}

	public List<TipologiaClientCount> getCategories() {
		return categories;
	}

	public void setCategories(List<TipologiaClientCount> categories) {
		this.categories = categories;
	}
}
