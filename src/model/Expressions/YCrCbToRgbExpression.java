package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorModel;


public class YCrCbToRgbExpression extends ParenExpression
{
    private YCrCbToRgbExpression ()
    {

    }


    public YCrCbToRgbExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> yCrCbtoRGB = new ArrayList<String>();
        yCrCbtoRGB.add("yCrCbtoRGB");
        return yCrCbtoRGB;
    }



    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new YCrCbToRgbExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        RGBColor toMake = ColorModel.ycrcb2rgb(results.get(0));
        return toMake;
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new YCrCbToRgbExpression());
    }

}
