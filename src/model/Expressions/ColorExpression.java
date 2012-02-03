package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class ColorExpression extends ParenExpression
{

    public ColorExpression (List<Expression> subexpressions)
    {
        super(subexpressions);

    }


    private ColorExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> result = evaluateSubExpressions(x, y, variableNames);
        return ColorCombinations.color(result.get(0),
                                       result.get(1),
                                       result.get(2));
    }


    public static ExpressionFactory getFactory ()
    {

        return new ExpressionFactory(new ColorExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> color = new ArrayList<String>();
        color.add("color");
        return color;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 3;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new ColorExpression(subExpressions);
    }

}
