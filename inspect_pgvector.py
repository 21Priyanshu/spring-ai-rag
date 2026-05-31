import zipfile, os
jar_path=os.path.expanduser('~/.m2/repository/com/pgvector/pgvector/0.1.6/pgvector-0.1.6.jar')
print('jar:', jar_path)
if not os.path.exists(jar_path):
    raise SystemExit('jar not found')
with zipfile.ZipFile(jar_path) as z:
    for name in z.namelist():
        if 'PGvector' in name or 'hibernate' in name.lower() or 'dialect' in name.lower() or 'type' in name.lower():
            print(name)
