package ch.heArc.hotelDiscovery.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CErrorController implements ErrorController  {
    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error/404";
    }

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
/*
public class CErrorController {
	
}
*/