package itcube.consulting.monitoraggioClient.response;

import java.util.HashMap;

public class GetClientTypesResponse extends GeneralResponse{
	private HashMap<String, Integer> categories;

	public GetClientTypesResponse() {
	}

	public HashMap<String, Integer> getCategories() {
		return categories;
	}

	public void setCategories(HashMap<String, Integer> categories) {
		this.categories = categories;
	}
}
