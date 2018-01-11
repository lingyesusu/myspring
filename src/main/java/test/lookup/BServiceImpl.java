package test.lookup;

import org.springframework.stereotype.Service;

@Service("bServiceImpl")
public class BServiceImpl implements AOrBService{
	public String say(){
		System.out.println("b");
		return "b";
	}

}
