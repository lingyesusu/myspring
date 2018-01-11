package test.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class AOrBFactory {
	
	@Lookup("aServiceImpl")
	public AOrBService a(){
		return null;
	}
	
	@Lookup("bServiceImpl")
	public AOrBService b(){
		return null;
		
	}

}
