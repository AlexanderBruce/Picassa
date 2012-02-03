package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class LogExpression extends ParenExpression
{
    private LogExpression ()
    {

    }


    public LogExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> log = new ArrayList<String>();
        log.add("log");
        return log;
    }

    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new LogExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.log(toMake.getRed()),
                            Math.log(toMake.getGreen()),
                            Math.log(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new LogExpression());
    }

}
