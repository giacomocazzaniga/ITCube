package itcube.consulting.monitoraggioClient.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;

public final class Services {

	private static HashMap<Integer, HashMap<String, Date>> AuthenticationManager= new HashMap<Integer,HashMap<String, Date>>();
	private static int milliSecLenghtToken=10000000;
	private static double threshold=0.1*milliSecLenghtToken;

	//Metodo token
	public static String getJWTToken(String ragione_sociale) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("ITCube")
				.setSubject(ragione_sociale)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + milliSecLenghtToken))
				.signWith(SignatureAlgorithm.HS512, 
				  secretKey.getBytes()).compact();

		return token;
	}
	
	public static void putToken(Integer id_company, String token)
	{
		HashMap<String, Date> newMap =new HashMap<String, Date>();
		newMap.put(token, new Date(System.currentTimeMillis() + milliSecLenghtToken));
		if(AuthenticationManager.containsKey(id_company))
		{
			AuthenticationManager.get(id_company).put(token, new Date(System.currentTimeMillis() + milliSecLenghtToken));
		}
		else
		{
			newMap.put(token, new Date(System.currentTimeMillis() + milliSecLenghtToken) );
			AuthenticationManager.put(id_company, newMap);
		}
	}

	public static String tokenCompany(Integer id_company)
	{
		if(AuthenticationManager.containsKey(id_company))
		{
			return (String) AuthenticationManager.get(id_company).keySet().toArray()[0];
		}
		else
		{
			return null;
		}
	}
	
	public static boolean isValid(Integer id_company, String token)
	{
		if(AuthenticationManager.containsKey(id_company))
		{
			if(AuthenticationManager.get(id_company).containsKey(token))
			{
				if((new Date(System.currentTimeMillis())).before(((Date)AuthenticationManager.get(id_company).get(token))))
				{
					return true;
				}
				else
				{
					AuthenticationManager.get(id_company).remove(token);
					return false;
				}
			}
			return false;
		}
		return false;
	}
	
	public static String checkThreshold(int id_company, String token)
	{
		if((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(id_company).get(token)), TimeUnit.MILLISECONDS))<threshold)
		{
			System.out.println((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(id_company).get(token)), TimeUnit.MILLISECONDS)));
			System.out.println(threshold);
			String newToken=getJWTToken(token.substring(0,10));
			AuthenticationManager.get(id_company).remove(token);
			AuthenticationManager.get(id_company).put(newToken,new Date(System.currentTimeMillis() + milliSecLenghtToken));
			return newToken;
		}
		return null;
	}
	
	
	public static ValidToken checkToken(int id_company, String token)
	{
		ValidToken validToken=new ValidToken();
		
		if(Services.isValid(id_company, token))
		{
			
			String newToken=Services.checkThreshold(id_company, token);
			validToken.setToken(newToken);
			validToken.setValid(true);
		}
		else
		{
			validToken.setToken(null);
			validToken.setValid(false);
		}
		return validToken;
	}
	
	/*public static String getLicenseCode()
	{
			String codice="";
			String shortId;

				for(int i=0; i<4; i++)
				{
					shortId = RandomStringUtils.randomAlphanumeric(4); 
					codice+=shortId;
					if(i<3)
						codice+="-";
				}
				//codice = UUID.randomUUID().toString();
			System.out.println(codice);
		    return codice;    	
	}*/
	
	public static String getLicenseKey() 
	{ 
		String MyKeys = "AB1CD2EF3GH4JK5LM6NP7QR8ST9UVZ";
		String LicenseKey = "";
		int CharControl = 0;
		int CharValue = 0;
		Random rand = new Random();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 7; j++) {
				int RandomChoice = rand.nextInt(MyKeys.length() - 0) + 0;
				CharValue += RandomChoice;
				LicenseKey = LicenseKey.concat(MyKeys.substring(RandomChoice, RandomChoice + 1));
			}
			CharControl += CharValue;
			int ctrlch = CharControl / LicenseKey.length();
			CharControl = (int) ctrlch;
			LicenseKey = LicenseKey.concat(MyKeys.substring(CharControl, CharControl + 1));
		}
		LicenseKey = LicenseKey.replaceAll("....", "$0-");
		LicenseKey = LicenseKey.substring(0, LicenseKey.length() - 1);
		return LicenseKey;
	} 
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public static Date getCurrentDate() {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();

		String dateAsString = sdf.format(date); 
		Date dateFromString = sdf.parse(dateAsString);
		return dateFromString;
		 */
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss z", Locale.ITALIAN);
		Date date =  new Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		LocalDateTime d = LocalDateTime.parse(timeStamp, formatter);
		return Date.from(d.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getScadenza()
	{
		return new Date(System.currentTimeMillis()+500000);
	}
}
