xcopy "${pHarness}\*.exe" "${pOut}"
xcopy "${pSE}\*.dll" "${pOut}"
xcopy "${pJBWJ}\*.jar" "${pOut}"
robocopy "${pFuzzRes}" "${pOut}" /xf "*.txt" /xd "META-INF" /s