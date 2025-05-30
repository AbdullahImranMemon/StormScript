package stormscript;

import java.util.List;
import java.util.Map;

/* Classes lox-class < Classes lox-class-callable
class LoxClass {
*/
//> lox-class-callable
class StormScriptClass implements StormScriptCallable {
    //< lox-class-callable
    final String name;
    //> Inheritance lox-class-superclass-field
    final StormScriptClass superclass;
    //< Inheritance lox-class-superclass-field
/* Classes lox-class < Classes lox-class-methods

  LoxClass(String name) {
    this.name = name;
  }
*/
//> lox-class-methods
    private final Map<String, StormScriptFunction> methods;

    /* Classes lox-class-methods < Inheritance lox-class-constructor
      LoxClass(String name, Map<String, LoxFunction> methods) {
    */
//> Inheritance lox-class-constructor
    StormScriptClass(String name, StormScriptClass superclass,
                     Map<String, StormScriptFunction> methods) {
        this.superclass = superclass;
//< Inheritance lox-class-constructor
        this.name = name;
        this.methods = methods;
    }
    //< lox-class-methods
//> lox-class-find-method
    StormScriptFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

//> Inheritance find-method-recurse-superclass
        if (superclass != null) {
            return superclass.findMethod(name);
        }

//< Inheritance find-method-recurse-superclass
        return null;
    }
//< lox-class-find-method

    @Override
    public String toString() {
        return name;
    }
    //> lox-class-call-arity
    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        StormScriptInstance instance = new StormScriptInstance(this);
//> lox-class-call-initializer
        StormScriptFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

//< lox-class-call-initializer
        return instance;
    }

    @Override
    public int arity() {
/* Classes lox-class-call-arity < Classes lox-initializer-arity
    return 0;
*/
//> lox-initializer-arity
        StormScriptFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
//< lox-initializer-arity
    }
//< lox-class-call-arity
}