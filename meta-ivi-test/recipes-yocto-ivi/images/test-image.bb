require recipes-yocto-ivi/images/ivi-image.bb

PV = "${DATE}"

IMAGE_INSTALL_append += " openssh test-fw \
    audiomanager-test audiomanagerplugins-test \
    common-api-test capicxx-perf \
    common-api-c++-dbus-test \
    persistence-client-library-test \
    persistence-common-object-test \
    persistence-administrator-test \
    dlt-daemon-test \
    node-state-manager-test \
    node-health-monitor-test \
    bluez5-noinst-tools bluez5-test \
    node-startup-controller-nsm-dummy glib-2.0-utils \
    vsomeip-test \
    wayland-ivi-extension-test \
    "

EXTRA_USERS_PARAMS = ""
