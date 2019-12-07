<<<<<<< HEAD
FROM httpd:latest
LABEL "Maintainer"="Paulo"
COPY pictolearn-sample/ /usr/local/apache2/htdocs/pictolearn
COPY httpd.conf /usr/local/apache2
=======
FROM httpd:alpine
LABEL "Maintainer" ="Pictolearn"

RUN echo 'installing website files'
COPY pictolearn-sample/ /usr/local/apache2/httpd

RUN echo 'installing apache config file'
COPY httpd.conf /usr/local/apache2/conf

>>>>>>> 775caf9e6e19a140691fe30d2dd6ef1da17738bc
