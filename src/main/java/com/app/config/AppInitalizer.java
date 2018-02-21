package com.app.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setForceEncoding(true);
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter};
    }

    //MULTIPART
    private static final String LOCATION = "C:/mytemp"; //katalog wykorzystywany podczas multipart
    private static final long MAX_FILE_SIZE = 5242880; //max rozmiar jednego przesylanego pliku w b
    private static final long MAX_REQUEST_SIZE = 20971520; //max rozmiar wszystkich plikow wysylanych w ramach jednego firomularza
    private static final int FILE_SIZE_THRESHOLD = 0; //powyzej takiego rozmiaru plik jest przesylany
    //z wykorzystaniem pomocniczego folderu w LOCATION

    private MultipartConfigElement multipartConfigElement()
    {
        return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(multipartConfigElement());
    }

}
