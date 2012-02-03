package model.Expressions;

import model.Parser;


public class ExpressionFactory
{

    private Expression myExpression;


    public ExpressionFactory (Expression expression)
    {
        myExpression = expression;
    }


    public boolean isThisKindOfExpression (Parser parser)
    {
        return myExpression.isThisKindOfExpression(parser);
    }


    public Expression parseExpression (Parser parser)
    {
        return myExpression.parseExpression(parser);
    }

}
