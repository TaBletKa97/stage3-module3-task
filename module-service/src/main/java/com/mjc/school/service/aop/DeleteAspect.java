package com.mjc.school.service.aop;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Aspect
public class DeleteAspect {
    private final Scanner sc;
    private final BaseRepository<News, Long> newsDao;

    @Autowired
    public DeleteAspect(NewsRepositoryImpl newsDao) {
        this.sc = new Scanner(System.in);
        this.newsDao = newsDao;
    }

    @Around("@annotation(com.mjc.school.service.aop.anotations.OnDelete) &&" +
            " args(id)")
    public Object cascadeDeletingAdvice(ProceedingJoinPoint jp, Long id) throws Throwable {
        System.out.println("Choose an deleting option:");
        System.out.println("1. Set authorId field for corresponding news to null");
        System.out.println("2. Remove corresponding news.");

        boolean isSetNull;
        String variant = sc.nextLine();
        switch (variant) {
            case "1" -> isSetNull = true;
            case "2" -> isSetNull = false;
            default -> throw new RuntimeException(String.format(
                    ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), variant
            ));
        }

        List<News> relatedNews = newsDao.readAll().stream()
                .filter(news -> news.getAuthor() != null)
                .filter(news -> news.getAuthor().getId().equals(id))
                .collect(Collectors.toList());
        if (isSetNull) {
            relatedNews.forEach(news -> news.setAuthor(null));
            for (News news : relatedNews) {
                newsDao.update(news);
            }

        } else {
            relatedNews.forEach(news -> newsDao.deleteById(news.getId()));
        }
        return jp.proceed();
    }
}
