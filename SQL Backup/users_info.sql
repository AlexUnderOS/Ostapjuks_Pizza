PGDMP  ,                    |        
   users_info    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398 
   users_info    DATABASE     �   CREATE DATABASE users_info WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE users_info;
                postgres    false            �            1259    16399    worker    TABLE     �   CREATE TABLE public.worker (
    name character varying(10) NOT NULL,
    password character varying(10) NOT NULL,
    admin boolean
);
    DROP TABLE public.worker;
       public         heap    postgres    false            �          0    16399    worker 
   TABLE DATA           7   COPY public.worker (name, password, admin) FROM stdin;
    public          postgres    false    215   2       �       x�KL����L�%\�9��`"�+F��� ���     