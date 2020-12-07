package com.vmware.mapbu.support.sessiontest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	private static final String COUNTER_SESSION_KEY = "COUNTER";

	@GetMapping()
	public String home(Model model, HttpSession session, HttpServletRequest req) {
		Integer count = (Integer) session.getAttribute(COUNTER_SESSION_KEY);
		log.debug("Count found is [" + count + "]");

		count = (count != null) ? count + 1 : 0;
		session.setAttribute(COUNTER_SESSION_KEY, count);
		log.debug("Count set, it is now [" + count + "]");

		model.addAttribute("count", count);
		model.addAttribute("ip", System.getenv("CF_INSTANCE_IP"));
		model.addAttribute("inst", System.getenv("CF_INSTANCE_INDEX"));
		model.addAttribute("guid", System.getenv("INSTANCE_GUID"));
		model.addAttribute("id", session.getId());

		return "home";
	}

	@GetMapping("/reset")
	public String reset(HttpSession session) {
		log.debug("Count was [" + session.getAttribute(COUNTER_SESSION_KEY) + "], resetting...");
		session.removeAttribute(COUNTER_SESSION_KEY);
		log.debug("Count is now [" + session.getAttribute(COUNTER_SESSION_KEY) + "]");
		return "redirect:/";
	}

}
