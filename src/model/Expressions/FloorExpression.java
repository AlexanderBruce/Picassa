package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class FloorExpression extends ParenExpression
{

    public FloorExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private FloorExpression ()
    {

    }


    @Override
    protected List<String> commandName ()
    {
        List<String> floor = new ArrayList<String>();
        floor.add("floor");
        return floor;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new FloorExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.floor(toMake.getRed()),
                            Math.floor(toMake.getGreen()),
                            Math.floor(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new FloorExpression());
    }

}
