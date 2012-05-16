DEFAULT_PREFERENCE = "0"

OTHER_PACKAGES_ivi = "\
    ${QT_BASE_NAME}-tools \
    ${QT_BASE_NAME}-assistant \
    ${QT_BASE_NAME}-common \
    ${QT_BASE_NAME}-dbus \
    ${QT_BASE_NAME}-designer \
    ${QT_BASE_NAME}-fonts \
    ${QT_BASE_NAME}-fonts-ttf-vera \
    ${QT_BASE_NAME}-fonts-ttf-dejavu \
    ${QT_BASE_NAME}-fonts-pfa \
    ${QT_BASE_NAME}-fonts-pfb \
    ${QT_BASE_NAME}-fonts-qpf \
    ${QT_BASE_NAME}-linguist \
    ${QT_BASE_NAME}-makeqpf \
    ${QT_BASE_NAME}-mkspecs \
    ${QT_BASE_NAME}-pixeltool \
    ${QT_BASE_NAME}-qmlviewer \
    ${QT_BASE_NAME}-xmlpatterns \
    ${QT_BASE_NAME}-qt3to4 \
    ${QT_BASE_NAME}-qml-plugins"

RRECOMMENDS_${PN} = "${LIB_PACKAGES} ${OTHER_PACKAGES_ivi}"
