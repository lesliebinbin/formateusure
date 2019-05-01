package au.edu.uq.configs;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClojureConfig {
    private IFn clojureRequire;


    public ClojureConfig() {
        this.clojureRequire = Clojure.var("clojure.core", "require");
    }

    public IFn getClojureFunc(String clojureNameSpace, String funcName) {
        this.clojureRequire.invoke(Clojure.read(clojureNameSpace));
        return Clojure.var(clojureNameSpace, funcName);
    }
}
