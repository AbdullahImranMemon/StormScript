package stormscript;


import java.util.List;

interface StormScriptCallable {
    //> callable-arity
    int arity();
    //< callable-arity
    Object call(Interpreter interpreter, List<Object> arguments);
}