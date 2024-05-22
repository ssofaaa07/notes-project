INSERT INTO notes(note_date, note_text) SELECT '2024-05-20', 'Ого! Это что? Моя первая заметка!?' WHERE NOT EXISTS (SELECT 1 FROM notes);

