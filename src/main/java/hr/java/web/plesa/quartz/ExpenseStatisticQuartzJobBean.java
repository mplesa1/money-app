package hr.java.web.plesa.quartz;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.repository.ExpenseRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ExpenseStatisticQuartzJobBean extends QuartzJobBean {

    @Autowired
    private ExpenseRepository repository;

    private String name;

    public void setName(String name) { this.name = name; }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        var expenseByGroup = repository.findAll().stream()
                .collect(Collectors.groupingBy(Expense::getExpenseType));

        expenseByGroup.forEach(
                (k, v) -> {
                    System.out.println(k.toString() +
                            " SUM " + v.stream().map(e -> e.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add) + " " +
                            " MIN" + v.stream().map(Expense::getAmount).min(Comparator.naturalOrder()).get() + " " +
                            " MAX" + v.stream().map(Expense::getAmount).max(Comparator.naturalOrder()).get()

                    );
                }
        );
    }
}
