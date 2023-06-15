FROM gitpod/workspace-postgres
USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 17.0.7-ms && \
    sdk default java 17.0.7-ms"
