package conf.interfaces;

public interface EndpointElement {

    String getName();
    String getDescription();
    Runnable getRunner();
}
