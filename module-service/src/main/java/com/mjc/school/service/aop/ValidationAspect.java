package com.mjc.school.service.aop;

import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    //todo refactor

    @Pointcut("execution(* com.mjc.school.service.implementation.NewsManagingServiceImpl.create(..))")
    public void createNewsPoint() {}

    @Pointcut("execution(* com.mjc.school.service.implementation.NewsManagingServiceImpl.update(..))")
    public void updateNewsPoint() {}

    @Pointcut("execution(* com.mjc.school.service.implementation.AuthorManagingServiceImpl.create(..))")
    public void createAuthorPoint() {}

    @Pointcut("execution(* com.mjc.school.service.implementation.AuthorManagingServiceImpl.update(..))")
    public void updateAuthorPoint() {}

    @Around("(createNewsPoint() || updateNewsPoint()) && args(request)")
    public Object validationNewsAdvice(ProceedingJoinPoint jp, NewsDTORequest request) throws Throwable {
        final String contentWord = "Content";
        final String titleWord = "Title";
        final int minLength = 5;
        final int maxContentLength = 255;
        final int maxTitleLength = 30;
        String title = request.getTitle();
        String content = request.getContent();
        if (title != null) {
            if (title.length() < minLength || title.length() > maxTitleLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(), titleWord,
                        minLength, maxTitleLength, titleWord, title) +
                        " Length is " + title.length() + ".");
            }
        }
        if (content != null) {
            if (content.length() < minLength || content.length() > maxContentLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(), contentWord,
                        minLength, maxContentLength, contentWord, content));
            }
        }
        return jp.proceed();
    }

    @Around("(createAuthorPoint() || updateAuthorPoint()) && args(request)")
    public Object validationAuthorAdvice(ProceedingJoinPoint jp, AuthorDTORequest request) throws Throwable {
        if (request.getName() != null) {
            final int minLength = 3;
            final int maxLength = 15;
            final String name = "Name";
            if (request.getName().length() < minLength ||
                    request.getName().length() > maxLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(),
                        name, minLength, maxLength, name, request.getName()));
            }
        }
        return jp.proceed();
    }
}
