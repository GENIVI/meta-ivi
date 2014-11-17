# FIX ME
# RRECOMMENDS from poky brings in bluez4 but meta-ivi uses bluez5. For now
# remove these "recommendations" until there will be a fix for this in poky.
# Read more:
# http://lists.openembedded.org/pipermail/openembedded-core/2014-November/098846.html
RRECOMMENDS_${PN} = ""
