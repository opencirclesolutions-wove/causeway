module org.apache.causeway.incubator.viewer.graphql.model {
    exports org.apache.causeway.viewer.graphql.model;
    exports org.apache.causeway.viewer.graphql.model.types;
    exports org.apache.causeway.viewer.graphql.model.registry;
    exports org.apache.causeway.viewer.graphql.model.domain;
    exports org.apache.causeway.viewer.graphql.model.exceptions;
    exports org.apache.causeway.viewer.graphql.model.context;
    exports org.apache.causeway.viewer.graphql.model.mmproviders;
    exports org.apache.causeway.viewer.graphql.model.fetcher;

    requires org.apache.causeway.core.config;
    requires org.apache.causeway.incubator.viewer.graphql.applib;
    requires spring.context;
    requires org.apache.causeway.core.metamodel;
    requires com.graphqljava;
    requires spring.boot.autoconfigure;
}