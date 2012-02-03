package model.Expressions;

import java.util.*;
import model.RGBColor;
import model.util.ColorCombinations;


public class SumExpression extends UnlimitedParenExpression
{

    public SumExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    private SumExpression(){
        
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> sum = new ArrayList<String>();
        sum.add("sum");
        return sum;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new SumExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x,y,variableNames,time);
        RGBColor sum = new RGBColor(0);
        for(RGBColor toAdd:result){
            sum = ColorCombinations.add(sum, toAdd);
        }
        return sum;

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new SumExpression());
    }

}
