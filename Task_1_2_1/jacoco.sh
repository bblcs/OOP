#!/bin/sh

gradle clean jacocoTestReport
firefox app/build/reports/jacoco/test/html/index.html
