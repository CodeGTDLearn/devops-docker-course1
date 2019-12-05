FROM httpd:alpine
LABEL "Maintainer" ="Pictolearn"

RUN echo 'installing website files'
COPY pictolearn-sample/ /usr/local/apache2/httpd

RUN echo 'installing apache config file'
COPY httpd.conf /usr/local/apache2/conf

