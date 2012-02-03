package model.Expressions;

import java.util.*;
import model.RGBColor;
import model.util.ColorCombinations;


public class ProductExpression extends UnlimitedParenExpression
{

    public ProductExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    private ProductExpression(){
        
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> product = new ArrayList<String>();
        product.add("product");
        return product;
    }

    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new ProductExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x,y,variableNames,time);
        RGBColor product = new RGBColor(1);
        for(RGBColor toMul:result){
            product = ColorCombinations.multiply(product, toMul);
        }
        return product;

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ProductExpression());
    }

}