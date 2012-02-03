package model.Expressions;

import java.util.*;
import model.RGBColor;

public class IfExpression extends ParenExpression
{

    public IfExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private IfExpression ()
    {

    }


    @Override
    protected List<String> commandName ()
    {
        List<String> myif = new ArrayList<String>();
        myif.add("if");
        return myif;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 3;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new IfExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        if(results.get(0).compareTo(new RGBColor(0))>0)
            return results.get(1);
        return results.get(2);
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new IfExpression());
    }

}
