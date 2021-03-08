package itcube.consulting.monitoraggioClient.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class Services {

	private static HashMap<String, Date> AuthenticationManager= new HashMap<String, Date>();
	private static int milliSecLenghtToken=30000;
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
	
	public static void putToken(String token)
	{
		AuthenticationManager.put(token, new Date(System.currentTimeMillis() + milliSecLenghtToken));
	}

	public static boolean isValid(String token)
	{
		if(AuthenticationManager.containsKey(token))
		{
			if((new Date(System.currentTimeMillis())).before(((Date)AuthenticationManager.get(token))))
			{
				return true;
			}
			else
			{
				AuthenticationManager.remove(token);
				return false;
			}
		}
		return false;
	}
	
	public static String checkThreshold(String token)
	{
		if((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(token)), TimeUnit.MILLISECONDS))<threshold)
		{
			System.out.println((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(token)), TimeUnit.MILLISECONDS)));
			System.out.println(threshold);
			String newToken=getJWTToken(token);
			AuthenticationManager.remove(token);
			AuthenticationManager.put(newToken,new Date(System.currentTimeMillis() + milliSecLenghtToken));
			return newToken;
		}
		return null;
	}
	
	public static String getLicenseCode()
	{
			String codice="";
			String shortId;

				for(int i=0; i<4; i++)
				{
					shortId = RandomStringUtils.randomAlphanumeric(4); 
					System.out.println(shortId);
					codice+=shortId;
					if(i<3)
						codice+="-";
				}
				//codice = UUID.randomUUID().toString();
			
		    return codice;    	
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	public static String getCurrentDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =  new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
