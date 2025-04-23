package stormscript;


import java.util.HashMap;
import java.util.Map;

class StormScriptInstance {
    private StormScriptClass klass;
    //> lox-instance-fields
    private final Map<String, Object> fields = new HashMap<>();
//< lox-instance-fields

    StormScriptInstance(StormScriptClass klass) {
        this.klass = klass;
    }

    //> lox-instance-get-property
    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

//> lox-instance-get-method
        StormScriptFunction method = klass.findMethod(name.lexeme);
/* Classes lox-instance-get-method < Classes lox-instance-bind-method
    if (method != null) return method;
*/
//> lox-instance-bind-method
        if (method != null) return method.bind(this);
//< lox-instance-bind-method

//< lox-instance-get-method
        throw new RuntimeError(name, // [hidden]
                "Undefined property '" + name.lexeme + "'.");
    }
    //< lox-instance-get-property
//> lox-instance-set-property
    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }
    //< lox-instance-set-property
    @Override
    public String toString() {
        return klass.name + " instance";
    }
}