PGDMP                      |        	   user_card    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16419 	   user_card    DATABASE     �   CREATE DATABASE user_card WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE user_card;
                postgres    false            �            1259    16420    card    TABLE     U   CREATE TABLE public.card (
    number text NOT NULL,
    balance double precision
);
    DROP TABLE public.card;
       public         heap    postgres    false            �          0    16420    card 
   TABLE DATA           /   COPY public.card (number, balance) FROM stdin;
    public          postgres    false    215   �       �   "   x�325�45743�45�3443��S�=... u`     