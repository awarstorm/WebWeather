package com.eaglecrk.awarstorm.WeatherController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eaglecrk.awarstorm.weather.WeatherModule;
import com.google.maps.errors.ApiException;

import tk.plogitech.darksky.forecast.ForecastException;



/**
 * Servlet implementation class WeatherController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/" })
public class WeatherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.getRequestDispatcher("/WEB-INF/weather-search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc = request.getParameter("location");
		WeatherModule w = new WeatherModule();
		double temp = 0;
		try {
			temp = w.getTemperatureF(loc);
		} catch (ForecastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("location", loc);
		request.setAttribute("temperature", temp);
		request.getRequestDispatcher("/WEB-INF/weather-result.jsp").forward(request, response);
	}

}
