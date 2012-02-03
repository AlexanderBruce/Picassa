package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class ModExpression extends ParenExpression
{

    public ModExpression (List<Expression> subExpressions)
    {

        super(subExpressions);
    }


    private ModExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> result = evaluateSubExpressions(x, y, variableNames);
        return ColorCombinations.divide(result.get(0), result.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ModExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> mod = new ArrayList<String>();
        mod.add("mod");
        mod.add("%");
        return mod;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new ModExpression(subExpressions);
    }

}
