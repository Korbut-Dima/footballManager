package com.footballManager.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class CalculationUtil {
    public static BigDecimal getCostOfTransfer(Float commissionForTransfer, Date dateOfBirth, Date startOfCareer) {
        LocalDate birthDate = dateOfBirth.toLocalDate();
        LocalDate careerStart = startOfCareer.toLocalDate();
        LocalDate current = LocalDate.now();


        Integer age = Period.between(birthDate, current).getYears();
        Integer experience = Period.between(careerStart, current).getMonths()+
                (Period.between(careerStart,current).getYears()*12);

        return BigDecimal.valueOf(experience*100000/age*(1+commissionForTransfer));
    }
}
