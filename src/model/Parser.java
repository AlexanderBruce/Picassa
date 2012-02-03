package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Expressions.*;


/**
 * Parses a string into an expression tree based on rules for arithmetic. Due to
 * the nature of the language being parsed, a recursive descent parser is used
 * http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{

    private static List<ExpressionFactory> myCommands;

    static
    {
        myCommands = new ArrayList<ExpressionFactory>();
        myCommands.add(ColorExpression.getFactory());
        myCommands.add(ConstantExpression.getFactory());
        myCommands.add(PlusExpression.getFactory());
        myCommands.add(DivExpression.getFactory());
        myCommands.add(MinusExpression.getFactory());
        myCommands.add(MulExpression.getFactory());
        myCommands.add(ModExpression.getFactory());
        myCommands.add(NegExpression.getFactory());
        myCommands.add(ExpExpression.getFactory());
        myCommands.add(XYExpression.getFactory());
        myCommands.add(MinExpression.getFactory());
        myCommands.add(MaxExpression.getFactory());
        myCommands.add(ProductExpression.getFactory());
        myCommands.add(AverageExpression.getFactory());
        myCommands.add(RandomExpression.getFactory());
        myCommands.add(FloorExpression.getFactory());
        myCommands.add(CeilExpression.getFactory());
        myCommands.add(AbsExpression.getFactory());
        myCommands.add(ClampExpression.getFactory());
        myCommands.add(WrapExpression.getFactory());
        myCommands.add(SinExpression.getFactory());
        myCommands.add(CosinExpression.getFactory());
        myCommands.add(TanExpression.getFactory());
        myCommands.add(AtanExpression.getFactory());
        myCommands.add(LogExpression.getFactory());
        myCommands.add(TimeExpression.getFactory());
        myCommands.add(RgbToYCrCbExpression.getFactory());
        myCommands.add(YCrCbToRgbExpression.getFactory());
        myCommands.add(PerlinColorExpression.getFactory());
        myCommands.add(PerlinBWExpression.getFactory());
        myCommands.add(SumExpression.getFactory());
        myCommands.add(LetExpression.getFactory());
        myCommands.add(IfExpression.getFactory());
        myCommands.add(VariableNameExpression.getFactory());
        
    }

    // state of the parser
    private int myCurrentPosition;
    private String myInput;


    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                              myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }


    public Expression parseExpression ()
    {

        for (ExpressionFactory exp : myCommands)
        {

            if (exp.isThisKindOfExpression(this))

            return exp.parseExpression(this);

        }

        throw new ParserException("Unexpected expression type");

    }


    public void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }


    public char currentCharacter ()
    {
        return myInput.charAt(myCurrentPosition);
    }


    public boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }


    /**
     * Pros: convenient, doesn't add classes Cons: Turns a private instance
     * variable into a pseudo public one as this method can be called outside
     * the parser I chose to do this instead of a wrapper class, or scanner
     * since those seemed to be baggage heavy
     */
    public void updatePosition (int change)
    {
        myCurrentPosition += change;
    }


    public boolean checkPattern (Pattern pattern)
    {
        Matcher matcher = pattern.matcher(myInput.substring(myCurrentPosition));
        return matcher.lookingAt();
    }


    public String grabExpressionKey (Pattern pattern)
    {
        Matcher matcher = pattern.matcher(myInput);
        matcher.find(myCurrentPosition);
        String expressionKey;
        if(currentCharacter()=='(')
        expressionKey = matcher.group(1);
        else
            expressionKey=matcher.group();
        return expressionKey;
    }




}
