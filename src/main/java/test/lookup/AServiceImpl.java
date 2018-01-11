package test.lookup;

import org.springframework.stereotype.Service;

@Service("aServiceImpl")
public class AServiceImpl implements AOrBService{
	
	public String say(){
		System.out.println("a");
		return "a";
	}

}