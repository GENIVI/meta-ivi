PRINC := "${@int(PRINC) + 1}"

# gnome-common is GPLv3 so we drop this dependency
# if INCOMPATIBLE_LICENSE contains GPLv3
python () {
    if (d.getVar("INCOMPATIBLE_LICENSE", True) or "").find("GPLv3") != -1:
        d.setVar("DEPENDS", " ".join(i for i in d.getVar("DEPENDS").split() if i != "gnome-common" and i != "gconf" and i != "gconf-native"))
}
