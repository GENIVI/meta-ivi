PRINC := "${@int(PRINC) + 1}"

# xz is GPLv3 so remove it from DEPENDS if INCOMPATIBLE_LICENSE contains GPLv3
python () {
    if (d.getVar("INCOMPATIBLE_LICENSE", True) or "").find("GPLv3") != -1:
        d.setVar("DEPENDS", " ".join(i for i in d.getVar("DEPENDS").split() if i != "xz"))
}
