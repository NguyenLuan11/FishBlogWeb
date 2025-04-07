package com.tnluan.fishblogweb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Global Exception
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("errors/global_error");
        modelAndView.addObject("message",
                "Something went wrong! Please try again later! \n" + ex.getMessage());
        return modelAndView;
    }

    // Handle ResourceNotFoundException and return 404_error page
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFoundException(ResourceNotFoundException nfEx) {
        ModelAndView modelAndView = new ModelAndView("errors/404_error");
        modelAndView.addObject("message", nfEx.getMessage());
        return modelAndView;
    }

    // Handle ResourceBadRequestException and return 400_error page
    @ExceptionHandler(ResourceBadRequestException.class)
    public ModelAndView handleBadRequestException(ResourceBadRequestException brEx) {
        ModelAndView modelAndView = new ModelAndView("errors/400_error");
        modelAndView.addObject("message", brEx.getMessage());
        return modelAndView;
    }

    // Handle ResourceConflictException and return 409_error page
    @ExceptionHandler(ResourceConflictException.class)
    public ModelAndView handleConflictException(ResourceConflictException cfEx) {
        ModelAndView modelAndView = new ModelAndView("errors/409_error");
        modelAndView.addObject("message", cfEx.getMessage());
        return modelAndView;
    }

    // Handle ResourceUnprocessableEntityException and return 422_error page
    @ExceptionHandler(ResourceUnprocessableEntityException.class)
    public ModelAndView handleUnprocessableEntityException(ResourceUnprocessableEntityException ueEx) {
        ModelAndView modelAndView = new ModelAndView("errors/422_error");
        modelAndView.addObject("message", ueEx.getMessage());
        return modelAndView;
    }

    // Handle ResourceInternalServerErrorException and return 500_error page
    @ExceptionHandler(ResourceInternalServerErrorException.class)
    public ModelAndView handleInternalServerErrorException(ResourceInternalServerErrorException iseEx) {
        ModelAndView modelAndView = new ModelAndView("errors/500_error");
        modelAndView.addObject("message", iseEx.getMessage());
        return modelAndView;
    }
}
