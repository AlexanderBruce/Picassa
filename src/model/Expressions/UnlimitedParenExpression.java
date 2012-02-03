package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import model.Parser;
import model.ParserException;


public abstract class UnlimitedParenExpression extends ParenExpression
{
    private int myParameters;
    protected UnlimitedParenExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
        myParameters=subExpressions.size();
    }


    /**
     * Java was implictly calling a unreachable superclass constructor so hizaah
     */
    protected UnlimitedParenExpression ()
    {
    }
    
    protected int numberOfParameters ()
    {
        
        return myParameters;
    }


    @Override
    public Expression parseExpression (Parser parser)
    {
        List<Expression> subexpressions = new ArrayList<Expression>();
        while (parser.notAtEndOfString() && parser.currentCharacter() != ')')
        {
            subexpressions.add(parser.parseExpression());
        }

        parser.skipWhiteSpace();
        if (parser.notAtEndOfString() && parser.currentCharacter() == ')')
        {
            parser.updatePosition(ONE);
            return constructParenExpression(subexpressions);
        }
        else
        {
            throw new ParserException("Expected close paren");
        }
    }

}
