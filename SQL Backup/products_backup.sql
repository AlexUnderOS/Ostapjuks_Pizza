PGDMP  6                    |           products    16.2    16.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16402    products    DATABASE     �   CREATE DATABASE products WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE products;
                postgres    false            �            1259    16403    dish    TABLE     �   CREATE TABLE public.dish (
    category text,
    price double precision,
    img text,
    name text,
    ingredient_list text,
    minutes integer
);
    DROP TABLE public.dish;
       public         heap    postgres    false            �            1259    16408    ingredient_quantity    TABLE     h   CREATE TABLE public.ingredient_quantity (
    ingredient text NOT NULL,
    quantity bigint NOT NULL
);
 '   DROP TABLE public.ingredient_quantity;
       public         heap    postgres    false            �            1259    16413    splitted_ingredients    TABLE     �   CREATE TABLE public.splitted_ingredients (
    dish_name text NOT NULL,
    ingredient text NOT NULL,
    quantity integer NOT NULL
);
 (   DROP TABLE public.splitted_ingredients;
       public         heap    postgres    false            �          0    16403    dish 
   TABLE DATA           T   COPY public.dish (category, price, img, name, ingredient_list, minutes) FROM stdin;
    public          postgres    false    215   s	       �          0    16408    ingredient_quantity 
   TABLE DATA           C   COPY public.ingredient_quantity (ingredient, quantity) FROM stdin;
    public          postgres    false    216          �          0    16413    splitted_ingredients 
   TABLE DATA           O   COPY public.splitted_ingredients (dish_name, ingredient, quantity) FROM stdin;
    public          postgres    false    217   �       �   �  x�ՔQO�0ǟ�O�GM	�Ȉ�	�)��d�uǨlk����[���O����.�]��H������d�62�)�<��5�!�Ȃ�s��U����jGqtH�ml/M8��%7H�2�Up���P��R�i��F�#f�,���Ȉk��J�X����F�J��eƎ���fV`U�y[D�"W��f�;RUe�fe7c/j�G��z�;�3�gѶc4��F�;6�N+��*���Ŏ� ˁ�O��9����)��{�[)vX�Zi�L٠4>?j|��J��9�X��T����V��Nq}��թNOz��[|�8��]�����j�Z��B���`
$(�B�{̝:](M�|8��@��@$������h���	C���y���y      �   �   x�E�K�0D��)zP(T��ހ=71iERW��ӓF V~c�X)G���$��@ۡ��#���k"e��)��_xz�>5� 	:|Q� }ߣM-���:��%%Ws��Z²��N���f��.
��d��Vh$۩�S��D��27߂E�<G�I/�F�����1��#"~ �YE�      �   �  x��T�n� <��=�R�?H��)�����:V����C��~�a��Z��d�f�����*^˲��G��42Vp�����"آ
r��ڂ�<�Gg2=r�,�}Hx���h��6�	'!�Do�$�mt���VQ-�����84Y	��{�A�x6��;}��w��k���[�&�'y-oq��is!���v��7�G�����d�|כhv����{)>y��Gj.�'4��Q��ed�g3��n}�H�g3����l;I�J��+�(g�iD��;��AVT�e�ӏ�HVs`E�#!Y�Ưݑ�jx�F�7`c�8��~���\'����6�5��>q�-��{1��~S}�kljp�\�P�&�X�1��M�A���d���J&�I�L�&m���Z�ɷ�(�� ��<     