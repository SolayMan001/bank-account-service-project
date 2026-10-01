package org.sid.bank_account_service.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomDataFetcherExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment environment) {
        if (exception instanceof ResponseStatusException response) {
            ErrorType type = switch (response.getStatusCode().value()) {
                case 400 -> ErrorType.BAD_REQUEST;
                case 404 -> ErrorType.NOT_FOUND;
                default -> ErrorType.INTERNAL_ERROR;
            };
            return GraphqlErrorBuilder.newError(environment)
                    .errorType(type)
                    .message(response.getReason())
                    .build();
        }
        return null;
    }
}
