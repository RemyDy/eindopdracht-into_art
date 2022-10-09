INSERT INTO user_account (username, password, email, enabled, locked)
VALUES ('Kahlan', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 'user@test.nl', TRUE, FALSE),
       ('Richard', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB2ica', 'artist@test.nl', TRUE, FALSE),
       ('Cara', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB3ica', 'admin@test.nl', TRUE, FALSE);

INSERT INTO authorities(authority)
VALUES ('USER'),
       ('ARTIST'),
       ('ADMIN');

INSERT INTO user_account_authority (user_account_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1);

INSERT INTO artist (user_account_id, forename, surname)
VALUES (2, 'Richard', 'Rahl');

INSERT INTO artwork ( title, description, price, dimensions, has_frame, has_passepartout, dimensions_incl_frame
                    , is_for_sale, is_sold)
VALUES ( 'sot', 'Richard is the Seeker', '100.00', '100cm x 100cm x 1cm', true, false, '110cm x 110cm x 1cm'
       , true, false);

INSERT INTO artist_artwork (artist_id, artwork_id)
VALUES (2, 1);

INSERT INTO newsletter(file_name, "subscription")
VALUES ('test', 'into_art_monthly');

INSERT INTO artist_newsletter(artist_id, newsletter_id)
VALUES (2, 1);

INSERT INTO subscriber(confirmation_token, email, name, token_confirmed_at, token_created_at, token_expired_at)
VALUES ( 'ThisWillBeAHashedCode', 'user@test.nl', 'Kahlan'
       , '20221009 10:31:00', '20221009 10:30:30', '20221009 10:45:00');

INSERT INTO newsletter_subscriber(newsletter_id, subscriber_id, subscriber_email, subscription)
VALUES (1, 1, 'user@test.nl', 'into_art_monthly');

-- @Docent, onderstaand de select queries die je direct in postgres kan gebruiken.
-- select * from user_account;
-- select * from authorities;
-- select * from user_account_authority;
-- select * from artist;
-- select * from artwork;
-- select * from artist_artwork;
-- select * from artist_newsletter;
-- select * from subscriber;
-- select * from newsletter;
-- select * from newsletter_subscriber;
